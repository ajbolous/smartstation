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

    
    return jsonify(stations)

@app.route('/stations/updateStatus', methods=['POST'])
def updateStatus():
    jsonContent = request.get_json()
    status = {}
    
    for jObj in jsonContent:
        stationId = jObj["stationId"]
        lineNumber = jObj["lineNumber"]
        stopType = jObj["stopType"]
      
        route = db.routes.getById(lineNumber)
        
        for stop in route.stops:
            if stop.stationId == stationId:
                originIndex = route.stops.index(stop)
                if stopType == 'Boarding':
                    route.stops[originIndex].gettingOn += 1 
                elif stopType == 'Alighting':
                    route.stops[originIndex].gettingOff += 1
        
                status[stop.stationId] = {
                    'stationId' : stationId,
                    'lineNumber' : lineNumber,
                    'stopType' : stopType,
                    'gettingOn' : route.stops[originIndex].gettingOn,
                    'gettingOff' : route.stops[originIndex].gettingOff
                } 
                
                db.routes.writeObjects()
                break

    return jsonify(status)

@app.route('/stations/stationReset', methods=['POST'])
def stationReset():
    jObj = request.get_json()
    stationId = jObj["stationId"]
    lineNumber = jObj["lineNumber"]

    route = db.routes.getById(lineNumber)
    if route:
        for stop in route.stops:
            if stop.stationId == stationId:
                stop.gettingOn = 0
                stop.gettingOff = 0
                db.routes.writeObjects()
                return jsonify({'success': True, 'message': 'Boarding And Alighting Information Has Been Reset'})

    return jsonify({'success': False, 'message': 'Could Not Reset The Station Information'})
   





