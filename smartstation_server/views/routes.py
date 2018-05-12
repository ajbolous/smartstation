from flask import request, jsonify
from .. import app
from ..database.database_handler import dbHandler
from ..model.algorithms import calcShortestPaths

@app.route("/getRoutes")
def getRoutes():
    return jsonify([route.toJson() for route in dbHandler.getRoutes()])

@app.route("/getShortestPath")
def getShortestPath():
    source = request.args.get('source')
    dest = request.args.get('dest')

    path = calcShortestPaths(dbHandler.getStations(), dbHandler.getRoutes(), source, dest)
    print(path)
    return jsonify(path)