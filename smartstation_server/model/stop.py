from .base import SerializableBase


class BusStop(SerializableBase):
    def __init__(self):
        self.stationId: str = ''
        self.timeFromOrigin: float = 0
        self.distanceFromOrigin: float = 0
        self.gettingOn: int = 0
        self.gettingOff: int = 0
