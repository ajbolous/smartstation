from .base import SerializableBase

class Login(SerializableBase):
    def __init__(self):
        self.username: str = 0
        self.password: str = 0
        