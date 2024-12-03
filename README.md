# zjoule

## Functions

- Call the Login Wizard - `LoginHandler` - Call an Eclipse wizard that uses the `SAP AI Core` **service key** to enable the user to select the **resource group** and the AI model **deployment Id** configured on BTP.

- Get AI Chat Response - `GetAiResponse` - Talks to the LLM using the **deployment id** selected through the login wizard.

## Memory

The following list of objects describes components that can handle data that is persisted across Eclipse sessions:

| Memory Object  | Type                           | Purpose         | Key             |
| -------------- | ------------------------------ | --------------- | --------------- |
| Access Token   | AccessToken.class `Serialized` | Authentication  | access-token    |
| Service Key    | ServiceKey.class `Serialized`  | Authentication  | service-key     |
| Resource Group | String                         | Chat            | resource-group  |
| Deployment     | String                         | Chat            | deployment      |

All of these memory objects are defined during the [login wizard](./com.developer.nefarious.zjoule/src/com/developer/nefarious/zjoule/login/) through a memory allocation process that uses the Eclipse preferences system at the instance level to persist data across sessions.

## Reference

[How this project consumes generative AI models.](https://help.sap.com/docs/sap-ai-core/sap-ai-core-service-guide/consume-generative-ai-models-using-sap-ai-core)