import json
from model.station import Station
from model.route import Route
import pymongo


class DatabaseHandler():
    def __init__(self):
        self.client = pymongo.MongoClient("")

    def getStations(self):
        stations = []
        with open("./data/stations.json", "r") as file:
            stationsArr = json.load(file)
            for stationObj in stationsArr:
                stations.append(Station.fromJson(stationObj))    
        return stations

    def getRoutes(self):
        routes = []
        with open("./data/routes.json", "r") as file:
            routesArr = json.load(file)
            for routeObj in routesArr:
                routes.append(Route.fromJson(routeObj))
        return routes
            

db = DatabaseHandler()
print(db.getStations())
print(db.getRoutes())