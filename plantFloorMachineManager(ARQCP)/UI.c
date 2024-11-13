#include <stdio.h>
#include <string.h>

void loginUI(char * username, char * password);
int login(char *username, char *password);
void showTeam();
void us_ui();


int main() {
    int option;
    char username[50];
    char password[50];
    
    while (1) {
        printf("╔══════════════════════════╗");
        printf("\n║        Main Menu         ║\n");
        printf("╚══════════════════════════╝\n");
        printf("║   1. Login               ║\n");
        printf("║   2. Show Team           ║\n");
        printf("║   0. Exit                ║\n");
        printf("╚══════════════════════════╝\n");

        printf("Select a option: ");
        scanf("%d", &option);

        switch (option) {
            case 1:
                loginUI(username,password);
                break;
            case 2:
                showTeam();
                break;
            case 0:
                printf("Saindo...\n");
                return 0;
            default:
                printf("Invalid choice. Try again...\n");
        }
    }

}

void us_ui(){
    int option;
    while (1) {
        printf("\n════════════════════════════\n");
        printf("            US_UI\n\n");
        printf(" 1. USAC01\n");
        printf(" 2. USAC02\n");
        printf(" 3. USAC03\n");
        printf(" 4. USAC04\n");
        printf(" 5. USAC05\n");
        printf(" 6. USAC06\n");
        printf(" 7. USAC07\n");
        printf(" 8. USAC08\n");
        printf(" 9. USAC09\n");
        printf(" 10. USAC10\n");
        printf(" 0. Exit  \n");
        printf("Select a option: ");
        scanf("%d", &option);

        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 0:
                printf("Exit...\n");
                return 0;
            default:
                printf("Invalid choice. Try again...\n");
        }
    }
}

void showTeam(){
    printf("\n════════════════════════════\n");
    printf("      Development Team\n\n");
    printf("Emanuel Almeida   - 1230839@isep.ipp.pt\n");
    printf("Jorge Ubaldo      - 1231274@isep.ipp.pt\n");
    printf("Francisco Santos  - 1230564@isep.ipp.pt\n");
    printf("Romeu Xu          - 1230444@isep.ipp.pt\n\n");
}



void loginUI(char * username, char * password){
    printf("\n════════════════════════════\n");
    printf("          Login UI\n\n");
    printf(" Username: ");
    scanf("%s", username);
    printf(" Password: ");
    scanf("%s", password);

    if (login(username, password)) {
        printf("\nLogin with success! Welcome-Back, %s.\n\n", username);
        us_ui();
    } else {
        printf("\nINVALID Username/Password.\n\n");
    }
        
}

int login(char *username, char *password) {

    char stored_username[] = "machmanager@this.app";
    char stored_password[] = "manager";
    
    if (strcmp(username, stored_username) == 0 && strcmp(password, stored_password) == 0) {
        return 1; 
    }
    return 0; 
}