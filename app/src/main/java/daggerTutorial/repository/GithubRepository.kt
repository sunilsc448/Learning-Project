package daggerTutorial.repository

import daggerTutorial.service.GitHubService
import javax.inject.Inject

class GithubRepository @Inject constructor(githubService:GitHubService) {
}