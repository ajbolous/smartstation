from flask import request, jsonify
from .. import app
from ..database import database as db

@app.route('/stations/getStationsHashed')
def getStationsHashed():
    return jsonify(db.stations.all(hashed=True))

@app.route('/stations/getStations')
def getStations():
    return jsonify(db.stations.all())


@app.route('/stations/getStationById')
def getStationById():
    stationId = request.args.get('stationId')
    return jsonify(db.stations.findById(stationId))

@app.route('/stations/getStationStatus')
def getStationStatus():
    stationId = request.args.get('stationId')
    station = db.stations.getById(stationId)
    return jsonify(station)
