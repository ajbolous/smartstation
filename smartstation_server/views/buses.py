from flask import request
from .. import app
from ..database.database import buses, jsonify
from ..model.algorithms import calcShortestPaths
from ..model.bus import Bus

@app.route('/buses/getBuses')
def getBuses():
    return jsonify(buses.all())


@app.route('/buses/getActiveBuses')
def getActiveBuses():
    return jsonify(buses.find(lambda bus: bus.isActive == True))


@app.route('/buses/setBusPosition')
def setBusPosition():
    id = request.args.get('id')
    lat = request.args.get('lat')
    lng = request.args.get('lng')

    bus = buses.getById(id)
    if bus is None:
        return 'Error cannot set position'

    bus.lat = lat
    bus.lng = lng
    buses.save()

    return 'Bus position was set successfuly'
