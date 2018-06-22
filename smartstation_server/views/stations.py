from flask import request, jsonify
from .. import app
from ..database import database as db

@app.route('/stations/getStationsHashed')
def getStationsHashed():
    return jsonify(db.stations.all(hashed=True))

@app.route('/stations/getStations')
def getStations():
    return jsonify(db.stations.all())


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
    stationId = request.args.get('stationId')
    _stations = []
    _stops = []
    for route in db.routes.all():
        for stop in route.stops:
            if stop.stationId == stationId:
                index = route.stops.index(stop)
                while index < (len(route.stops)-1):
                    _stations.append(route.stops[index+1])
                    index += 1
                break
    
    for stop in _stations:
        for station in db.stations.all():
            if stop.stationId == station.id:
               _stops.append(station)
    return jsonify(_stops)