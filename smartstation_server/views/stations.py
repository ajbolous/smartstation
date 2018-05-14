from flask import request, jsonify
from .. import app
from ..database.database_handler import dbHandler


@app.route('/stations/getStations')
def getStations():
    return jsonify(dbHandler.stations.all())


@app.route('/stations/getStationById')
def getStationById():
    '''
        Returns a station by its id. 
        if not found returns None
    '''
    stationId = request.args.get('stationId')
    
    return jsonify(dbHandler.stations.findById(stationId))
