# zJoule :sparkles:

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=The-Nefarious-Developer_zjoule&metric=alert_status&token=e2a58dc0706342532d8d14ae0badfe72fd08ff28)](https://sonarcloud.io/summary/new_code?id=The-Nefarious-Developer_zjoule) 
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**zJoule** is an Open Source AI assistant plugin for Eclipse designed to integrate the **SAP AI Core** Generative AI Hub foundation-models into your development environment.

<div align="center">
  <img src="docs/intro.gif" alt="Intro GIF" width="250"/>
</div>

## Documentation Content:
- [Requirements]()
- [Compatibility]()
- [Motivation]()
- [Benefits]()
- [Getting Started]()
- [References]()

## Requirements
- SAP AI Core with the `Extended` service plan (for the Generative AI Hub enablement).
- Eclipse 2024-09 (4.33.0) or latest. *Compatibility with older versions is not guaranteed.*

## Compatibility

The following table outlines the Generative AI models compatible with this plugin.

| Executable ID | Model              | Version       |
|---------------|--------------------|---------------|
| azure-openai  | `gpt-35-turbo`     | 0613          |
| azure-openai  | `gpt-35-turbo`     | 1106 (latest) |
| azure-openai  | `gpt-35-turbo-16k` | 0613 (latest) |
| azure-openai  | `gpt-4`            | 0613          |
| azure-openai  | `gpt-4-32k`        | 0613 (latest) |

LLM models that are not compatible may be selected during the login process; however, their functionality cannot be guaranteed, and errors may occur.

Additional model compatibilities may be introduced in the near future :)

> **Privacy Information** <br>
We know how privacy is important and ensure that all communication handled by the plugin is secure **is a must**. Here's how your data is managed:
> - **Data Flow:** All communication facilitated by the plugin occurs exclusively between Eclipse and the SAP AI Core model APIs. No external intermediaries are involved.
> - **Data Storage:** Any data processed or generated during plugin usage is stored locally within Eclipse's memory mechanism, ensuring it remains private and confined to your development environment. <br>
>
> By keeping all operations local and secure, the plugin should provide a trustworthy and seamless experience.

## Motivation

The official Joule version for ADT is being rolled out exclusively for S/4HANA Private Cloud and SAP BTP, ABAP Environment at the end of Q1 of 2025. But don’t worry, this plugin was created to bridges the gap! It introduces an AI-powered chat interface that seamlessly connects other types of SAP systems to large language models (LLMs) through SAP AI Core.

The idea is to open the door to integrate SAP ABAP development with new models and innovative features that could emerge from the expanding possibilities of AI. 

All from the cozy confines of your Eclipse ADT =)

### Benefits

#### 1. Coding Assistance:
- Answer coding-related questions.
- Provide real-time suggestions and explanations for code.
- Assist in debugging by analyzing error messages and suggesting fixes.
#### 2. Documentation & Learning:
- Summarize or explain code snippets and concepts.
- Fetch and clarify information on APIs, libraries, or frameworks.
#### 3. Productivity Boost:
- Reduce context switching by enabling developers to query information directly within Eclipse.
- Automate repetitive tasks through scripting and custom commands.
#### 4. Integration with SAP AI Core:
- Access advanced AI capabilities, potentially tailored to SAP-specific development workflows.
#### 5. Dynamic Testing Support:
- Offer insights into test cases or suggest ways to optimize testing processes.
#### 6. Natural Language Interaction:
- Simplify complex development tasks by allowing users to describe their needs in plain language.
#### 7. Enhanced Development Experience:
- Enable faster learning for beginners or new team members.
- Provide a smoother workflow for experienced developers needing quick solutions.

## Reference

[How this project consumes generative AI models.](https://help.sap.com/docs/sap-ai-core/sap-ai-core-service-guide/consume-generative-ai-models-using-sap-ai-core)

[SAP Note: 3437766 (Availability of Generative AI Models)](https://me.sap.com/notes/3437766)

## License
Copyright (c) 2024 The Nefarious Developer <br />
Licensed under the MIT License. See [LICENSE](LICENSE).