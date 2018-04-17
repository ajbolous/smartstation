from .base import SerializableBase
from .stop import BusStop

class Route(SerializableBase):
    def __init__(self):
        self.number = 0
        self.stops = []

    def toJson(self):
        return {
            "number": self.number,
            "stops": [stop.toJson() for stop in self.stops]
        }
    
    @classmethod
    def fromJson(cls, obj):
        route = Route()
        route.number = obj["number"]
        route.stops = [BusStop.fromJson(stop) for stop in obj["stops"]]
        return route


