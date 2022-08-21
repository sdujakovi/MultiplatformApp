//
//  GithubRepositoryItem.swift
//  iosApp
//
//  Created by Stanko Dujaković on 21.08.2022..
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI
import struct Kingfisher.KFImage

struct GithubRepositoryItem: View {
    
    var image: String
    var name: String
    var owner: String
    
    var body: some View {
        HStack(alignment: .center) {
            
            KFImage(URL(string: image))
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: 70)
                            .cornerRadius(20)
                            .padding(.trailing)
            
            VStack(alignment: .leading) {
                
                Text(name)
                    .font(.system(size: 20, weight: .bold, design: .default))
                    .foregroundColor(.black)
                    .lineLimit(1)
                
                Text(owner)
                    .font(.system(size: 12, weight: .bold, design: .default))
                    .foregroundColor(.gray)
                    .lineLimit(1)
            }.padding(.trailing, 20)
            
            Spacer()
        }
        .frame(maxWidth: .infinity, alignment: .center)
        
        .background(Color(red: 230/255, green: 246/255, blue: 223/255))
        .cornerRadius(20)
        .shadow(color: Color.black.opacity(0.2), radius: 8, x: 0, y: 0)
        .padding(.all, 5)
        .compositingGroup()
    }
}