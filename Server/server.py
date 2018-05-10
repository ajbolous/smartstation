from flask import Flask, jsonify, request
from database_handler import DatabaseHandler
import algorithms 

app = Flask(__name__)
app.debug = True

dbHandler = DatabaseHandler()


@app.route("/getStations")
def getStations():
    return jsonify([station.toJson() for station in dbHandler.getStations()])


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
    

app.run()
