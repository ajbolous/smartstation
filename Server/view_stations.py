from flask import request, jsonify
from server import app
from database_handler import dbHandler


@app.route("/getStations")
def getStations():
    return jsonify([station.toJson() for station in dbHandler.getStations()])

@app.route('getStationById')
def getStationById():
    '''
        Returns a station by its id. 
        if not found returns None
    '''
    pass