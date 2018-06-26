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
    stationId = str(request.args.get('stationId'))
    stations = {}

    for route in db.routes.all():
        originStop = next(filter(lambda stop: stop.stationId == stationId, route.stops), None)
        print(originStop)
        if not originStop:
            continue
        originIndex = route.stops.index(originStop)

        for stop in route.stops[originIndex : ]:
            distance = abs(stop.distanceFromOrigin - originStop.distanceFromOrigin)
            if distance < 10:
                if stop.stationId not in stations:
                    stations[stop.stationId] = {
                        'station' : db.stations.getById(stop.stationId),
                        'distance' : distance,
                        'routes' : [route.id]
                    }
                else:
                    stations[stop.stationId]['routes'].append(route.id)

<<<<<<< HEAD
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


        





=======
    return jsonify(list(stations.values()))
>>>>>>> 6da2ec7854b06b4a6fc71e9b39a48497b01b7b1d
