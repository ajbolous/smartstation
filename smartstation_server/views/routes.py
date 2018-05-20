from flask import request, jsonify
from .. import app
from ..database.database import routes, stations
from ..model.algorithms import calcShortestPaths
from ..model.route import Route

@app.route('/routes/getRoutes')
def getRoutes():
    return jsonify(routes.all())

@app.route('/routes/getShortestPath')
def getShortestPath():
    source = request.args.get('source')
    dest = request.args.get('dest')

    path = calcShortestPaths(stations.all(), routes.all(), source, dest)
    print(path)
    return jsonify(path)

@app.route('/routes/getRoutesFromStation')
def getRoutesFromStation():
    stationId = request.args.get('stationId')
    _routes = []
    for route in routes.all():
        for stop in route.stops:
            if stop.stationId == stationId:
                _routes.append(route)
                break
    return jsonify(_routes)
