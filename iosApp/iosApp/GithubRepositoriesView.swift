//
//  RepositoriesView.swift
//  iosApp
//
//  Created by Stanko Dujaković on 20.08.2022..
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct GithubRepositoriesView: View {
    @ObservedObject var state: GithubRepositoriesState
    @State var searchQuery = ""
    
    init() {
        state = GithubRepositoriesState(text: "sduja")
    }
    
    var body: some View {
        NavigationView{
            List(state.repositories, id: \.id) { repo in
                Text(repo.name!)
            }
            .listStyle(PlainListStyle())
            .searchable(text: $searchQuery)
            .navigationTitle("Github Repo Searcher")
        }
    }
}
