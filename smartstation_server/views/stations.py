from flask import request, jsonify
from .. import app
from ..database.database_handler import dbHandler


@app.route('/stations/getStations')
def getStations():
    return jsonify(dbHandler.stations.all())


@app.route('/stations/getStationById')
def getStationById():
    stationId = request.args.get('stationId')

    for station in dbHandler.getStations():
        if station.id == stationId:
    stationId = request.args.get('stationId')
    
    return jsonify(dbHandler.stations.findById(stationId))

        "message": "None"
    })  
    