import json
from model.station import Station
from model.route import Route
from model.Login import Login
import pymongo


class DatabaseHandler():
    def __init__(self):
        pass

    def getStations(self):
        stations = []
        with open("Server/data/stations.json", "r") as file:
            stationsArr = json.load(file)
            for stationObj in stationsArr:
                stations.append(Station.fromJson(stationObj))    
        return stations

    def getRoutes(self):
        routes = []
        with open("Server/data/routes.json", "r") as file:
            routesArr = json.load(file)
            for routeObj in routesArr:
                routes.append(Route.fromJson(routeObj))
        return routes
            
    def getLogin(self):
        login = []
        with open("Server/data/json/Driver.json", "r") as file:
            loginArr = json.load(file)
            for loginObj in loginArr:
                login.append(Login.fromJson(loginObj))
        return login

db = DatabaseHandler()