# Contributing to this Project

Thank you for considering contributing to this project! Your contributions are greatly appreciated and help us improve and evolve. Below are some guidelines to follow when contributing.

- [Code of conduct](#code-of-conduct)
- [How to contribute](#how-to-contribute)
- [Using the issue tracker](#using-the-issue-tracker)
- [Code Quality and Testing](#code-quality-and-testing)
- [Getting Your Pull Request Merged](#getting-your-pull-request-merged)

## Code of conduct

Help us keep this project open and inclusive. Please read and follow our [Code of conduct](CODE_OF_CONDUCT.md).

## How to Contribute

1. **Fork the Repository**: Start by forking the repository on GitHub.
2. **Create a Branch**: Make a feature branch to work on.
3. **Make Your Changes**: Ensure you make clear, focused commits for each change.
4. **Submit a Pull Request**: When you're ready, submit a pull request (PR) with a detailed description of what your changes accomplish.

## Commit Message Guidelines

We follow the **Angular Semantic Release** pattern for commit messages. This ensures clear and structured commit history, which helps in understanding the purpose of changes and generating changelogs. 

### Commit Message Format

Each commit message should include a type and a description.

### Allowed Types

The type must be one of the following:

| Type         | Description                                                                                              |
| ------------ | -------------------------------------------------------------------------------------------------------- |
| **feat**     | A new feature (this triggers a new version release).                                                     |
| **fix**      | A bug fix.                                                                                               |
| **docs**     | Documentation-only changes.                                                                              |
| **style**    | Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc.). |
| **refactor** | A code change that neither fixes a bug nor adds a feature.                                               |
| **test**     | Adding missing or correcting existing tests.                                                             |
| **chore**    | Changes to the build process or auxiliary tools and libraries such as documentation generation.          |
| **try**      | Operation that needs to be tested through an unconventional method (pipeline and others)                 |


Adding a new feature is highly recommended as it contributes significantly to the project's growth.

### Atomic commits

If possible, make [atomic commits](https://en.wikipedia.org/wiki/Atomic_commit), which means:

- a commit should contain exactly one self-contained functional change
- a functional change should be contained in exactly one commit
- a commit should not create an inconsistent state (such as test errors, linting errors, partial fix, feature without documentation, etc...)

A complex feature can be broken down into multiple commits as long as each one maintains a consistent state and consists of a self-contained change.

## Using the Issue Tracker

We encourage contributors to use the issue tracker for all suggestions, feature requests, and bug reports. When you submit a PR, reference any related issue to give context.

- **Feature Requests**: If you have a new idea or enhancement, feel free to create an issue labeled as "feature request".
- **Bug Reports**: If you've encountered a bug, please submit an issue with as much detail as possible.

## Code Quality and Testing

- **Tests are Mandatory**: All changes must be accompanied by relevant tests. Contributions without tests will not be accepted.
- **Linting**: Ensure that your code adheres to the project's linting rules. PRs should not introduce any linting errors.

## Getting Your Pull Request Merged

1. Ensure that all tests are passing.
2. Make sure your code doesn't introduce any linting errors.
3. Ensure your commit messages follow the guidelines mentioned above.
4. Include updates to documentation if necessary.

---

Thank you for taking the time to read this! We look forward to your contributions.