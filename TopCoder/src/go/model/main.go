package model

import "fmt"

func gophertest() {
	gopher1Name := "Phil"
	gopher1Age := 30
	if gopher1Age < 65 {
		highJump(gopher1Name)
	} else {

	}
}

func highJump(name string) {
	fmt.Println(name, "can jump high")
}

type gopher struct {
	name string
	age int
	isAdult bool
}

type horse struct {
	name string
	weight int
}

func (h horse) jump() string {
	if h.weight > 2500 {
		return "I'm too heavy, can't jump..."
	}
	return "I will jump, neigh!!"
}

type jumper interface {
	jump() string
}

func (g gopher) jump() string {
	if g.age < 65 {
		return g.name + " can jump high"
	}

	return g.name + " can still jump"
}

func validateAge(g *gopher) {
	g.isAdult = g.age >= 21
}

func GetList() []jumper {
	phil := &gopher{name : "Phil", age :30}
	noodles := &gopher {name : "Noodles", age : 30}
	barbaro := &horse{name : "Barbaro", weight : 2000}
	list := []jumper{phil, noodles, barbaro}
	return list
}
