from flask import request
from .. import app
from ..database.database import stations
from flask import jsonify
from flask.json import JSONEncoder


class CustomJSONEncoder(JSONEncoder):
    def default(self, obj):
        return obj.__dict__
        return JSONEncoder.default(self, obj)

app.json_encoder = CustomJSONEncoder

@app.route('/stations/getStations')
def getStations():
    return jsonify(stations.all())


@app.route('/stations/getStationById')
def getStationById():
    stationId = request.args.get('stationId')
    return jsonify(stations.findById(stationId))
