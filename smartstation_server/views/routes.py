from flask import request, jsonify
from .. import app
from ..database import database as db
from ..model.algorithms import calcShortestPaths
from ..model.route import Route


@app.route('/routes/getRoute')
def getRoute():
    routeId = int(request.args.get('routeId'))
    route = db.routes.getById(routeId)
    for stop in route.stops:
        stop.station = db.stations.getById(stop.stationId)
    return jsonify(route)


@app.route('/routes/getRoutes')
def getRoutes():
    return jsonify(db.routes.all())


@app.route('/routes/getShortestRoute')
def getShortestPath():
    source = request.args.get('sourceId')
    dest = request.args.get('destinationId')
    paths = calcShortestPaths(db.stations.all(), db.routes.all(), source, dest)
    shortestRoutes = []
    for path in paths:
        shortestRoute = {
            'stations' : [],
            'totalDistance' : 0
        }
        for stationId in path:
            shortestRoute['stations'].append(db.stations.getById(stationId))
            shortestRoute['totalDistance'] += 1
        shortestRoutes.append(shortestRoute)
        print (shortestRoute)
    return jsonify(shortestRoutes)


@app.route('/routes/getRoutesFromStation')
def getRoutesFromStation():
    stationId = request.args.get('stationId')
    _routes = []
    for route in db.routes.all():
        for stop in route.stops:
            if stop.stationId == stationId:
                _routes.append(route)
                break
    return jsonify(_routes)


@app.route('/routes/getAvailableRoutes')
def getAvailable():
    driverId = request.args.get('driverId')
    driver = db.users.getById(driverId)
    print(driverId)
    return jsonify(db.routes.find(lambda o: driver.operator in o.operator))
