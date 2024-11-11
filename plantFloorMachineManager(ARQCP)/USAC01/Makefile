executable: main.o usac01.o
	gcc main.o usac01.o -z noexecstack -o executable

main.o: main.c
	gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o

usac01.o: usac01.s
	gcc -g -Wall -Wextra -fanalyzer -c usac01.s -o usac01.o

clean:
	rm -f *.o executable

run: executable
	./executable