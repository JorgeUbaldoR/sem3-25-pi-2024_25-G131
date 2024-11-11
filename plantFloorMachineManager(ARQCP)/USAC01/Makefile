executable: files/main.o files/usac01.o | files
	gcc files/main.o files/usac01.o -z noexecstack -o files/executable

files/main.o: src/main.c | files
	gcc -g -Wall -Wextra -fanalyzer -c src/main.c -o files/main.o

files/usac01.o: src/usac01.s
	gcc -g -Wall -Wextra -fanalyzer -c src/usac01.s -o files/usac01.o

clean:
	rm -f files/*.o files/executable

run: executable
	files/./executable

