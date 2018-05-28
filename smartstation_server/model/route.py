from .base import SerializableBase
from .stop import BusStop
from typing import List,Dict


class Route(SerializableBase):
    def __init__(self):
        self.number: int = 0
        self.stops: List[BusStop] = []
        self.operator: str = 'Eged'

    def toJson(self):
        return {
            'id' : self.number,
            'number': self.number,
            'stops': [stop.toJson() for stop in self.stops],
            'operator' : self.operator
        }

    @classmethod
    def fromJson(cls, obj):
        route = Route()
        route.id = obj['number']
        route.number = obj['number']
        route.stops = [BusStop.fromJson(stop) for stop in obj['stops']]
        route.operator = obj['operator']
        return route
