import json
import pymongo

from ..model.station import Station
from ..model.route import Route
from ..model.user import User

class DatabaseHandler():
    def __init__(self):
        pass

    def loadAsObjects(self, filename, classType):
        with open(filename, "r") as file:
            return [classType.fromJson(obj) for obj in json.load(file)]

    def getStations(self):
        return self.loadAsObjects('smartstation_server/data/stations.json', Station)

    def getRoutes(self):
        return self.loadAsObjects('smartstation_server/data/routes.json', Route)

    def getUsers(self):
        return self.loadAsObjects('smartstation_server/data/users.json', User)


dbHandler = DatabaseHandler()
