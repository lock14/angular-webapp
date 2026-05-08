---
name: use-feature-branches
description: Enforces a Git feature branch workflow. Use whenever making code changes, committing, or pushing to the repository to prevent direct commits to primary branches like main or master.
---

# Use Feature Branches

When interacting with the git repository or modifying code, you MUST always follow a feature branch workflow. Under no circumstances should you commit code directly to the primary branches (such as `main`, `master`, or `develop`).

## When to use this skill

- Use this when you are tasked with writing code, fixing bugs, or modifying the repository.
- This is helpful for ensuring all changes are isolated, reviewable, and tested before being merged into the main codebase.

## How to use it

1. **Check Current Branch:** Always verify which branch you are currently on by running `git status` or `git branch`.
2. **Create a Feature Branch:** Before making any code changes, create and checkout a new branch from the primary branch. Use a descriptive name for the branch. 
   - Examples: `git checkout -b feature/short-description-of-task` or `git checkout -b bugfix/issue-description`.
3. **Commit Changes:** Once your changes are complete and tested, add and commit them to your feature branch with a clear and descriptive commit message.
4. **Push Branch:** Push the feature branch to the remote repository.
5. **Never Commit to Main:** Ensure that your changes are kept isolated to the feature branch to allow for proper code review and testing before merging.
