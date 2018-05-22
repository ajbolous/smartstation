from flask import request, jsonify
from .. import app
from ..database.database import stations, jsonify

@app.route('/stations/getStations')
def getStations():
    return jsonify(stations.all())


@app.route('/stations/getStationById')
def getStationById():
    stationId = request.args.get('stationId')
    return jsonify(stations.findById(stationId))
