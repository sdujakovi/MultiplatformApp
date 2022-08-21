//
//  GithubRepositoriesState.swift
//  iosApp
//
//  Created by Stanko Dujaković on 20.08.2022..
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class GithubRepositoriesState: ObservableObject{
    let viewModel: GithubViewModel
    
    @Published private(set) var repositories: [Repository] = []
    
    init(text: String) {
        viewModel = GithubViewModel(query: text)
        viewModel.observeGithubRepositories { repositories in
            self.repositories = repositories
        }
    }
    
    func searchRep(searchText: String){
        viewModel.getGithubRepositories(query: searchText)
    }
    
    deinit {
        viewModel.dispose()
    }
}