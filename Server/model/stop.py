from .base import SerializableBase

class BusStop(SerializableBase):
    def __init__(self):
        self.stationId = 0
        self.timeToNext = 0
        self.distanceToNext = 0

