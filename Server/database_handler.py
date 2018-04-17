import json
from model.station import Station
import pymongo

class DatabaseHandler():
    def __init__(self):
        self.client = pymongo.MongoClient("mongodb://smartstations:eJz5FxWFCbkF39YqOe3hbUUWe9YVwE3V9Btv1cVNuMtESDtsVt9CA5qJIxTESVWeeQhZlgrzLTtOrPMUK8Ijeg==@smartstations.documents.azure.com:10255/?ssl=true&replicaSet=globaldb")
        self.stations = self.getStations()

    def getStations(self):
        stationObjects = []
        file = open("/SS-Git/smartstation/Server/data/30.json", mode="r", encoding="utf8")
        stations = json.load(file)
        for station in stations:
            stationObjects.append(fromJson(station))
        return stationObjects

