from .base import SerializableBase

class BusStop(SerializableBase):
    def __init__(self):
        self.stationId: str= 0
        self.timeFromOrigin: float = 0
        self.distanceFromOrigin: float = 0

