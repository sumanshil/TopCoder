package main

import (
	"time"
	"fmt"
	"errors"
	"os"
)

func main() {
	currentHour := time.Now().Hour()
	result, err := getGreetings(currentHour)
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}

	fmt.Println(result)
}

func getGreetings(currentHour int) (string,error) {
	var message string
	if currentHour < 7 {
		err := errors.New("Too early for greetings!")
		return message, err
	}
	if currentHour < 12 {
		message = "Good morning"
	} else if currentHour < 18 {
		message = "Good afternoon"
	} else {
		message = "Good evening"
	}
	return message, nil
}