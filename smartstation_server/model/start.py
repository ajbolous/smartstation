from .base import SerializableBase


class Start(SerializableBase):
    def __init__(self):
        self.number: int = 0
        self.firstStation: str = ''
        self.lastStation: str = ''