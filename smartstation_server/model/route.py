from .base import SerializableBase
from .stop import BusStop
from typing import List,Dict


class Route(SerializableBase):
    def __init__(self):
        self.company: str = ''
        self.number: int = 0
        self.stops: List[BusStop] = []

    def toJson(self):
        return {
            'company': self.company,
            'number': self.number,
            'stops': [stop.toJson() for stop in self.stops]
        }

    @classmethod
    def fromJson(cls, obj):
        route = Route()
        route.company = obj['company']
        route.number = obj['number']
        route.stops = [BusStop.fromJson(stop) for stop in obj['stops']]
        return route
