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
import struct Kingfisher.KFImage

struct GithubRepositoriesView: View {
    @ObservedObject var state: GithubRepositoriesState
    @State var searchQuery = ""
    
    init() {
        state = GithubRepositoriesState(text: "")
    }
    
    var body: some View {
            NavigationView{
                VStack(spacing: 0) {
                    SearchBar(
                        text: $searchQuery,
                        onTextChanged: searchGithubRepositories
                    )
                    .navigationTitle("Search a Repository")
                    .background(Color(red: 255/255, green: 249/255, blue: 252/255))
                    if(state.anyUseCaseInProgres.boolValue) {
                        ProgressView()
                            .progressViewStyle(CircularProgressViewStyle())
                            .scaleEffect(3)
                            .frame(maxWidth: .infinity, maxHeight: .infinity)
                            .tint(Color(red: 239/255, green: 223/255, blue: 246/255))
                            .background(Color(red: 255/255, green: 249/255, blue: 252/255))
                    } else {
                        List(state.repositories, id: \.id) { repo in
                            GithubRepositoryItem(image: (repo.owner?.avatarUrl)!, name: repo.name!, owner: repo.name!)
                                .listRowSeparator(.hidden)
                        }
                        .listStyle(PlainListStyle())
                        .background(Color(red: 255/255, green: 249/255, blue: 252/255))
                    }
                }
            }
    }
    
    func searchGithubRepositories(searchText: String) {
            if !searchText.isEmpty {
                state.searchRep(searchText: searchText)
            }
        }
}

struct SearchBar: UIViewRepresentable {
    @Binding var text: String
    var onTextChanged: (String) -> Void
    
    class Coordinator: NSObject, UISearchBarDelegate {
        var onTextChanged: (String) -> Void
        @Binding var text: String
        
        init(text: Binding<String>, onTextChanged: @escaping (String) -> Void) {
            _text = text
            self.onTextChanged = onTextChanged
        }
        
        func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
            text = searchText
            //onTextChanged(text)
        }
        
        func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
            //text = searchText
            onTextChanged(text)
            searchBar.resignFirstResponder()
        }
    }
    
    func makeCoordinator() -> SearchBar.Coordinator {
        return Coordinator(text: $text, onTextChanged: onTextChanged)
    }
    
    func makeUIView(context: UIViewRepresentableContext<SearchBar>) -> UISearchBar {
        let searchBar = UISearchBar()
        searchBar.delegate = context.coordinator
        searchBar.searchBarStyle = .minimal
        searchBar.autocapitalizationType = .none
        searchBar.layer.cornerRadius = 20
        searchBar.barStyle = UIBarStyle.black
        searchBar.barTintColor = UIColor.black
        searchBar.searchTextField.backgroundColor = UIColor(Color(red: 239/255, green: 223/255, blue: 246/255))
        searchBar.layer.shadowRadius = 8
        searchBar.placeholder = "Search a repo..."
        
        return searchBar
    }
    
    func updateUIView(_ uiView: UISearchBar, context: UIViewRepresentableContext<SearchBar>) {
        uiView.text = text
    }
}