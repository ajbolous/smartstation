from flask import request, jsonify
from .. import app
from ..database.database_handler import dbHandler
from ..model.algorithms import calcShortestPaths
from ..model.route import Route

@app.route('/routes/getRoutes')
def getRoutes():
    return jsonify(dbHandler.routes.all())

@app.route('/routes/getShortestPath')
def getShortestPath():
    source = request.args.get('source')
    dest = request.args.get('dest')

    path = calcShortestPaths(dbHandler.stations.all(), dbHandler.routes.all(), source, dest)
    print(path)
    return jsonify(path)

@app.route('/routes/getRoutesFromStation')
def getRoutesFromStation():
    pass