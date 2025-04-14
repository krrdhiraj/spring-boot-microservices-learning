import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
    realm : "E-learnApp",
    url: "http://localhost:9091",
    clientId: "elearn-app",
    pkceMethod: "s256"
});

export default keycloak;