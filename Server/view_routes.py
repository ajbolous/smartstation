from flask import request, jsonify
from server import app
from database_handler import dbHandler
import algorithms

@app.route("/getRoutes")
def getRoutes():
    return jsonify([route.toJson() for route in dbHandler.getRoutes()])

@app.route("/getShortestPath")
def getShortestPath():
    source = request.args.get('source')
    dest = request.args.get('dest')

    path = algorithms.calcShortestPaths(dbHandler.getStations(), dbHandler.getRoutes(), source, dest)
    print(path)
    return jsonify(path)