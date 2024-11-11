executable: main.o usac10.o ../USAC09/usac09.o
	gcc main.o usac10.o ../USAC09/usac09.o -z noexecstack -o executable

main.o: main.c
	gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o

usac10.o: usac10.s ../USAC09/usac09.s
	gcc -g -Wall -Wextra -fanalyzer -c usac10.s -o usac10.o

../USAC09/usac09.o: ../USAC09/usac09.s
	gcc -g -Wall -Wextra -fanalyzer -c ../USAC09/usac09.s -o ../USAC09/usac09.o

clean:
	rm -f *.o ../USAC09/usac09.o executable

run: executable
	./executable
