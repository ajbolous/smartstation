from flask import request, jsonify
from .. import app
from ..database import database as db


@app.route('/stations/getStationsHashed')
def getStationsHashed():
    return jsonify(db.stations.all(hashed=True))


@app.route('/stations/getStations')
def getStations():
    return jsonify(db.stations.all())


@app.route('/stations/getFavouriteStations')
def getFavouriteStations():
    stationIds = ['50840', '50592', '53950']
    stationsList = [{'station': db.stations.getById(
        stationId), 'distance': 0, 'routes': []} for stationId in stationIds]
    return jsonify(stationsList)


@app.route('/stations/getStationById')
def getStationById():
    stationId = request.args.get('stationId')
    return jsonify(db.stations.findById(stationId))


@app.route('/stations/getStationStatus')
def getStationStatus():
    stationId = request.args.get('stationId')
    station = db.stations.getById(stationId)
    return jsonify(station)


@app.route('/stations/getPossibleStations')
def getPossibleStations():
    stationId = str(request.args.get('stationId'))
    stations = {}

    for route in db.routes.all():
        originStop = next(
            filter(lambda stop: stop.stationId == stationId, route.stops), None)

        for stop in route.stops:
            distance = abs(stop.distanceFromOrigin -
                           originStop.distanceFromOrigin) if originStop else 0
            if distance < 100:
                if stop.stationId not in stations:
                    stations[stop.stationId] = {
                        'station': db.stations.getById(stop.stationId),
                        'distance': distance,
                        'routes': [route.id]
                    }
                else:
                    stations[stop.stationId]['routes'].append(route.id)

    return jsonify(list(stations.values()))
