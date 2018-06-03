from ..model.station import Station
from ..model.route import Route
from ..model.user import User
from ..model.bus import Bus
from ..model.base import SerializableBase
from flask import jsonify as flask_jsonify
from .json_cache import JsonCache

stations = JsonCache(
    'smartstation_server/database/data/stations.json', Station, hashed=True)
buses = JsonCache(
    'smartstation_server/database/data/buses.json', Bus, hashed=True)
users = JsonCache(
    'smartstation_server/database/data/users.json', User, hashed=True)
routes = JsonCache(
    'smartstation_server/database/data/routes.json', Route, hashed=True)


def save():
    stations.save()
    buses.save()
    users.save()
    routes.save()
