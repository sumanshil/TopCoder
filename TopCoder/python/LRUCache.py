class Node:
    def __init__(self, key, val):
        self.value = val
        self.key = key
        self.next: Node = None
        self.prev: Node = None


class LRUCache:
    def __init__(self, capacity: int):
        self.max_capacity = capacity
        self.map_entries = {}
        self.head = Node(-1, -1)
        self.tail = self.head
        self.curr = 0

    def get(self, key: int) -> int:
        if key in self.map_entries.keys():
            node: Node = self.map_entries[key]
            self.movetolast(node)
            return node.value
        else:
            return -1

    def put(self, key: int, value: int) -> None:
        if len(self.map_entries.keys()) == self.max_capacity:
            if key in self.map_entries.keys():
                node: Node = self.map_entries[key]
                self.movetolast(node)
                node.value = value
            else:
                node: Node = self.head.next
                self.movetolast(node)
                del self.map_entries[node.key]
                node.value = value
                node.key = key

        else:
            if key in self.map_entries.keys():
                node: Node = self.map_entries[key]
                node.value = value
                self.movetolast(node)
            else:
                node = Node(key, value)
                self.movetolast(node)

        self.map_entries[key] = self.tail

    def movetolast(self, node: Node):

        if node == self.tail:
            return

        if node.prev:
            node.prev.next = node.next

        if node.next:
            node.next.prev = node.prev

        node.next = None
        node.prev = None

        self.tail.next = node
        node.prev = self.tail

        self.tail = node


if __name__ == "__main__":
    cache = LRUCache(2)
    cache.put(1, 1)
    cache.put(2, 2)
    print(cache.get(1))
    cache.put(3, 3)
    print(cache.get(2))
    cache.put(4, 4)
    print(cache.get(1))
    print(cache.get(3))
    print(cache.get(4))