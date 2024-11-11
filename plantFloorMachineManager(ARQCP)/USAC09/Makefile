main: main.o usac09.o
	gcc main.o usac09.o -z noexecstack -o main
	
main.o: main.c 
	gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o
	
usac09.o: usac09.s
	gcc -g -Wall -Wextra -fanalyzer -c usac09.s -o usac09.o

run: main
	./main
	
clean:
	rm -f *.o main
