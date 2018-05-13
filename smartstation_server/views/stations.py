from flask import request, jsonify
from .. import app
from ..database.database_handler import dbHandler


@app.route("/getStations")
def getStations():
    return jsonify([station.toJson() for station in dbHandler.getStations()])

@app.route('/getStationById')
def getStationById():
    stationId = request.args.get('stationId')

    for station in dbHandler.getStations():
        if station.id == stationId:
            return jsonify(station.toJson())
                
    return jsonify({
        "success": False,
        "message": "None"
    })  
    