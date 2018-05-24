from flask import request, jsonify
from .. import app
from ..database import database as db
from ..model.algorithms import calcShortestPaths
from ..model.route import Route


@app.route('/routes/getRoutes')
def getRoutes():
    return jsonify(db.routes.all())


@app.route('/routes/getShortestPath')
def getShortestPath():
    source = request.args.get('source')
    dest = request.args.get('dest')
    path = calcShortestPaths(db.stations.all(), db.routes.all(), source, dest)
    return jsonify(path)


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


@app.route('/routes/getAvailable')
def getAvailable():
    driverId = request.args.get('driverId')
    driver = db.users.getById(driverId)
    _routes = db.routes.find(lambda o: driver.operator in o.operators)
    return jsonify(_routes)