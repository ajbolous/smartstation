import json
import pymongo

from ..model.station import Station
from ..model.route import Route
from ..model.user import User
from ..model.bus import Bus

from .json_cache import JsonCache


class DatabaseHandler():
    def __init__(self):
        self.stations = JsonCache(
            'smartstation_server/database/data/stations.json', Station, hashed=True)
        self.buses = JsonCache(
            'smartstation_server/database/data/buses.json', Bus)
        self.users = JsonCache(
            'smartstation_server/database/data/users.json', User, hashed=True)
        self.routes = JsonCache(
            'smartstation_server/database/data/routes.json', Route)

dbHandler = DatabaseHandler()
