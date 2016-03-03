package es.beni.testing.exercise4;

import es.beni.testing.exercise4.entities.Crm;
import es.beni.testing.exercise4.interfaces.AuthenticationService;
import es.beni.testing.exercise4.interfaces.ConfigService;
import es.beni.testing.exercise4.interfaces.Interactor;
import es.beni.testing.exercise4.interfaces.InteractorResponse;
import es.beni.testing.exercise4.interfaces.OUpdates;

public class SaveUserInteractor implements Interactor<InteractorResponse<OUpdates>> {

    private final AuthenticationService authenticationService;
    private final ConfigService configService;

    private Crm crm;

    public SaveUserInteractor(AuthenticationService authenticationService,
                              ConfigService configService) {

        this.authenticationService = authenticationService;
        this.configService = configService;
    }

    public void setCrm(Crm crm) {
        this.crm = crm;
    }

    public InteractorResponse<OUpdates> call() {
        authenticationService.saveUser(crm);

        InteractorResponse<OUpdates> boOrchextraUpdates = configService.refreshConfig();

        return boOrchextraUpdates;
    }
}
