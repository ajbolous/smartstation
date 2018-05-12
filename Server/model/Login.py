from .base import SerializableBase

class Login(SerializableBase):
    def __init__(self):
        self.username: str = ""
        self.password: str = ""
        