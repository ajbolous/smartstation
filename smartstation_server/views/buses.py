from flask import request, jsonify
from .. import app
from ..database.database_handler import dbHandler
from ..model.algorithms import calcShortestPaths
from ..model.bus import Bus


@app.route('/buses/getBuses')
def getBuses():
    return jsonify(dbHandler.buses.all())


@app.route('/buses/getActiveBuses')
def getActiveBuses():
    return jsonify(dbHandler.buses.find(field='isActive', value=True))


@app.route('/buses/setBusPosition')
def setBusPosition():
    id = request.args.get('id')
    lat = request.args.get('lat')
    lng = request.args.get('lng')

    bus = dbHandler.buses.getById(id)
    if bus is None:
        return 'Error cannot set position'

    bus.lat = lat
    bus.lng = lng
    dbHandler.buses.save()

    return 'Bus position was set successfuly'
