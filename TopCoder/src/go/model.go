package main

import (
	"fmt"
	"go/model"
)


func main()  {
	jumperList := model.GetList()
	/*
	langs := []string{"Go", "Ruby", "Javascript"}
	for _, element := range langs {
		fmt.Println(element)
	}

	fmt.Println(langs)
	*/
	/*
	gopher1 := gopher{name:"Phil", age:30}
	fmt.Println(gopher1.jump())
	*/
	//gophertest()
	/*
	gopher1 := &gopher{name:"Phil", age:30}
	validateAge(gopher1)
	fmt.Println(gopher1)
	*/
	gopherList := getList()
	for _, gopher := range gopherList {
		fmt.Println(gopher.jump())
	}
}
