executable: main.o usac02.o
	gcc main.o usac02.o -z noexecstack -o executable

main.o: main.c
	gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o

usac02.o: usac02.s
	gcc -g -Wall -Wextra -fanalyzer -c usac02.s -o usac02.o

clean:
	rm -f *.o executable

run: executable
	./executable