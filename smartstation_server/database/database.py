import json
from flask import jsonify as flask_jsonify
from ..model.station import Station
from ..model.route import Route
from ..model.user import User
from ..model.bus import Bus

from .json_cache import JsonCache


stations = JsonCache('smartstation_server/database/data/stations.json', Station, hashed=True)
buses = JsonCache('smartstation_server/database/data/buses.json', Bus)
users = JsonCache('smartstation_server/database/data/users.json', User, hashed=True)
routes = JsonCache('smartstation_server/database/data/routes.json', Route)

def jsonify(objects):
    if type(objects) == dict:
        return flask_jsonify(objects)
    if type(objects) == list:
        return flask_jsonify([obj.toJson() for obj in objects])
    return flask_jsonify(objects.toJson())